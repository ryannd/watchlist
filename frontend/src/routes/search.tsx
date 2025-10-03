import { SearchPage } from '@/features/search/SearchPage'
import { createFileRoute } from '@tanstack/react-router'

export const Route = createFileRoute('/search')({
  component: SearchPage,
})