import { useEffect, useState } from 'react'
import useSearch from '@/features/search/hooks/useSearch'
import SearchBar from '@/features/search/components/SearchBar'
import SearchResultCard from '@/features/search/components/SearchResultCard'
import { ScrollArea } from '@/components/ui/scroll-area'
import DynamicPagination from '@/components/DynamicPagination'

export const SearchPage = () => {
  const [searchQuery, setSearchQuery] = useState('')
  const [page, setPage] = useState(1)
  const [totalPages, setTotalPages] = useState(0)
  const { status, data, error } = useSearch(searchQuery, page)

  useEffect(() => {
    if (data && data.totalPages && data.totalPages != totalPages) {
      setTotalPages(data.totalPages)
    }
  }, [data])

  return (
    <div className="flex max-w-screen min-w-screen max-h-screen min-h-screen justify-center items-center">
      <div className="w-4xl flex flex-col gap-4">
        <SearchBar setSearchQuery={setSearchQuery} searchQuery={searchQuery} />
        <ScrollArea className="rounded-md border p-4 h-256">
          <div className="gap-4 flex flex-col">
            {status === 'pending' && searchQuery !== '' ? (
              'Loading...'
            ) : status === 'error' ? (
              <span>Error: {error.message}</span>
            ) : (
              <>
                {data?.results.map((result) => (
                  <SearchResultCard result={result} />
                ))}
              </>
            )}
          </div>
        </ScrollArea>
        <DynamicPagination
          currentPage={page}
          totalPages={totalPages}
          onPageChange={(newPage) => setPage(newPage)}
        />
      </div>
    </div>
  )
}
