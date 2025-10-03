import { createFileRoute } from '@tanstack/react-router'
import { SearchPage } from '@/features/search/pages/SearchPage'

export const Route = createFileRoute('/search')({
  component: SearchPage,
})
