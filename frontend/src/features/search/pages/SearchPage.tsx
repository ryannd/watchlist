import { useState } from 'react'
import useSearch from '@/features/search/hooks/useSearch'
import SearchBar from '@/features/search/components/SearchBar'
import SearchResultCard from '@/features/search/components/SearchResultCard'
import { ScrollArea } from '@/components/ui/scroll-area'

export const SearchPage = () => {
  const [searchQuery, setSearchQuery] = useState('')
  const { status, data, error } = useSearch(searchQuery)

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
                {data?.map((result) => (
                  <SearchResultCard result={result} />
                ))}
              </>
            )}
          </div>
        </ScrollArea>
      </div>
    </div>
  )
}
