import type { SearchResult } from '@/features/search/types/searchResult.type'
import { Card, CardContent } from '@/components/ui/card'

export default function SearchResultCard({ result }: { result: SearchResult }) {
  return (
    <Card className="overflow-hidden relative h-full">
      {result.backdropPath !== '' && (
        <div className="absolute inset-0">
          <img
            src={result.backdropPath}
            alt={result.title}
            className="w-full h-full object-cover"
          />
          <div className="absolute inset-0 bg-black/80" />
        </div>
      )}
      <CardContent className="relative h-full p-6 flex flex-col justify-center text-white">
        <h3 className="text-xl font-semibold mb-2">{result.title}</h3>
        <p className="text-muted-foreground line-clamp-2 text-white">
          {result.overview}
        </p>
      </CardContent>
    </Card>
  )
}
