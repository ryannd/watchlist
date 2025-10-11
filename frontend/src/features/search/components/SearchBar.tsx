import { LuSearch } from 'react-icons/lu'
import {
  InputGroup,
  InputGroupAddon,
  InputGroupButton,
  InputGroupInput,
} from '@/components/ui/input-group'

export default function SearchBar({
  searchQuery,
  setSearchQuery,
}: {
  searchQuery: string
  setSearchQuery: React.Dispatch<React.SetStateAction<string>>
}) {
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchQuery(e.target.value)
  }

  return (
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
  )
}
