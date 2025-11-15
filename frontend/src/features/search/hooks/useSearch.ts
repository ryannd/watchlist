import { useQuery } from '@tanstack/react-query'
import { useEffect, useRef, useState } from 'react'
import type { SearchResponse } from '@/features/search/types'

export default function useSearch(query: string, page: number) {
  const [debounced, setDebounced] = useState(query)

  useEffect(() => {
    const id = setTimeout(() => setDebounced(query), 200)
    return () => clearTimeout(id)
  }, [query])

  const abortRef = useRef<AbortController | null>(null)

  return useQuery({
    queryKey: [`search-${query}-${page}`, debounced],
    enabled: debounced.trim().length > 1,
    staleTime: 15_000,
    queryFn: async (): Promise<SearchResponse> => {
      abortRef.current?.abort()
      const controller = new AbortController()
      abortRef.current = controller
      const resp = await fetch(
        `${import.meta.env.VITE_API_BASE_URL}/search/?query=${encodeURIComponent(debounced)}&page=${page}`,
        { signal: controller.signal },
      )
      if (!resp.ok) throw new Error('Search failed')
      return resp.json()
    },
  })
}
