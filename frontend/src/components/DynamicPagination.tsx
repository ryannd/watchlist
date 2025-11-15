import {
  Pagination,
  PaginationContent,
  PaginationEllipsis,
  PaginationItem,
  PaginationLink,
  PaginationNext,
  PaginationPrevious,
} from '@/components/ui/pagination'

interface DynamicPaginationProps {
  currentPage: number
  totalPages: number
  onPageChange: (newPage: number) => void
}

export default function DynamicPagination({
  currentPage,
  totalPages,
  onPageChange,
}: DynamicPaginationProps) {
  const pageRange = 2

  const getPageNumbers = () => {
    const pages = []
    const startPage = Math.max(1, currentPage - pageRange)
    const endPage = Math.min(totalPages, currentPage + pageRange)

    if (startPage > 1) {
      pages.push(1)
      if (startPage > 2) pages.push('ellipsis-start')
    }

    for (let i = startPage; i <= endPage; i++) {
      pages.push(i)
    }

    if (endPage < totalPages) {
      if (endPage < totalPages - 1) pages.push('ellipsis-end')
      pages.push(totalPages)
    }

    return pages
  }

  const pageNumbers = getPageNumbers()

  return (
    <Pagination>
      <PaginationContent>
        <PaginationItem>
          <PaginationPrevious
            onClick={() => onPageChange(currentPage - 1)}
            aria-disabled={currentPage <= 1}
            tabIndex={currentPage <= 1 ? -1 : undefined}
            className={
              currentPage <= 1 ? 'pointer-events-none opacity-50' : undefined
            }
          />
        </PaginationItem>

        {pageNumbers.map((page, index) => (
          <PaginationItem key={index}>
            {page === 'ellipsis-start' || page === 'ellipsis-end' ? (
              <PaginationEllipsis />
            ) : (
              <PaginationLink
                isActive={page === currentPage}
                onClick={() => onPageChange(page as number)}
              >
                {page}
              </PaginationLink>
            )}
          </PaginationItem>
        ))}

        <PaginationItem>
          <PaginationNext
            onClick={() => onPageChange(currentPage + 1)}
            aria-disabled={currentPage == totalPages}
            tabIndex={currentPage == totalPages ? -1 : undefined}
            className={
              currentPage == totalPages
                ? 'pointer-events-none opacity-50'
                : undefined
            }
          />
        </PaginationItem>
      </PaginationContent>
    </Pagination>
  )
}
