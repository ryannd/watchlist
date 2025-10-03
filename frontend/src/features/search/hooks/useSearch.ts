import { useQuery } from '@tanstack/react-query'
import { useEffect, useRef, useState } from 'react'
import type { SearchResult } from '@/features/search/types/searchResult.type'

export default function useSearch(query: string) {
  const [debounced, setDebounced] = useState(query)

  useEffect(() => {
    const id = setTimeout(() => setDebounced(query), 200)
    return () => clearTimeout(id)
  }, [query])

  const abortRef = useRef<AbortController | null>(null)

  return useQuery({
    queryKey: ['search', debounced],
    enabled: debounced.trim().length > 1,
    staleTime: 15_000,
    queryFn: async (): Promise<Array<SearchResult>> => {
      abortRef.current?.abort()
      const controller = new AbortController()
      abortRef.current = controller
      const resp = await fetch(
        `http://localhost:8080/api/search/?query=${encodeURIComponent(debounced)}`,
        { signal: controller.signal },
      )
      if (!resp.ok) throw new Error('Search failed')
      return resp.json()
    },
  })
}
