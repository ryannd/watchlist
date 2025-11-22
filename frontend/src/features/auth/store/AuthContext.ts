import { createContext } from 'react'
import type { IAuth } from '@/features/auth/types'
import { firebaseAuth } from '@/integrations/firebase'

export const AuthContext = createContext<IAuth>({
  user: firebaseAuth.currentUser,
  loading: false,
  signIn: () => {},
  signUp: () => {},
  signOut: () => {},
})
