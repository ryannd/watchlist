export type SearchResult = {
  id: number
  title: string
  overview: string
  backdropPath: string
  posterPath: string
  mediaType: string
  releaseDate: string
}

export type SearchResponse = {
  results: Array<SearchResult>
  currentPage: number
  totalPages: number
}
