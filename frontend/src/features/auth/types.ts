import type { User } from 'firebase/auth'

export interface IAuth {
  user: User | null // type User comes from firebase
  loading: boolean
  signIn: (creds: LoginFormValues) => Promise<void>
  signUp: (creds: UserFormValues) => Promise<void>
  signOut: () => Promise<void>
}

export interface LoginFormValues {
  email: string
  password: string
}

export interface UserFormValues {
  email: string
  password: string
  displayName: string
}
