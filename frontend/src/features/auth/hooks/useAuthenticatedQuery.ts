import { useQuery } from '@tanstack/react-query'
import type { UseQueryOptions } from '@tanstack/react-query'

export default function useAuthenticatedQuery<
  TQueryKey extends [string, Record<string, unknown>?],
  TQueryFnData,
  TError,
  TData = TQueryFnData,
>(
  queryKey: TQueryKey,
  fetcher: (params: TQueryKey[1], token: string) => Promise<TQueryFnData>,
  options?: Omit<
    UseQueryOptions<TQueryFnData, TError, TData, TQueryKey>,
    'queryKey' | 'queryFn'
  >,
) {
  const authToken = localStorage.getItem('auth-token')

  return useQuery({
    queryKey,
    queryFn: async () => {
      return fetcher(queryKey[1], authToken ?? '')
    },
    ...options,
  })
}
