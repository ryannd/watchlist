import { createServerFn } from '@tanstack/react-start'
import { getCookie, setCookie } from '@tanstack/react-start/server'
import type { Theme } from '@/components/theme-provider'

const storageKey = 'ui-theme'

export const getThemeServerFn = createServerFn().handler(async () => {
  return (getCookie(storageKey) || 'dark') as Theme
})

export const setThemeServerFn = createServerFn({ method: 'POST' }).handler(
  async (ctx) => {
    const { data } = ctx
    if (typeof data != 'string' || (data != 'dark' && data != 'light')) {
      throw new Error('Invalid theme provided')
    }

    setCookie(storageKey, data as Theme)
  },
)
