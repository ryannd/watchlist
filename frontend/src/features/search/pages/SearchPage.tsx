import { useState } from 'react'
import { LuSearch } from 'react-icons/lu'
import { Card } from '@/components/ui/card'
import {
  InputGroup,
  InputGroupAddon,
  InputGroupButton,
  InputGroupInput,
} from '@/components/ui/input-group'
import useSearch from '@/features/search/hooks/useSearch'

export const SearchPage = () => {
  const [searchQuery, setSearchQuery] = useState('')
  const { status, data, error } = useSearch(searchQuery)
  console.log(status)
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchQuery(e.target.value)
  }

  return (
    <div className="flex max-w-screen min-w-screen max-h-screen min-h-screen justify-center items-center">
      <div className="w-4xl flex flex-col gap-4">
        <InputGroup>
          <InputGroupInput
            placeholder="Search..."
            value={searchQuery}
            onChange={handleInputChange}
          />
          <InputGroupAddon>
            <LuSearch />
          </InputGroupAddon>
          <InputGroupAddon align="inline-end">
            <InputGroupButton>Search</InputGroupButton>
          </InputGroupAddon>
        </InputGroup>
        <Card>
          {status === 'pending' ? (
            'Loading...'
          ) : status === 'error' ? (
            <span>Error: {error.message}</span>
          ) : (
            <>{data.map((e) => e.title)}</>
          )}
        </Card>
      </div>
    </div>
  )
}
