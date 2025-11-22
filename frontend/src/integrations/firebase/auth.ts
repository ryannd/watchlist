import {
  browserLocalPersistence,
  createUserWithEmailAndPassword,
  setPersistence,
  signInWithEmailAndPassword,
  signOut,
} from 'firebase/auth'
import type { LoginFormValues, UserFormValues } from '@/features/auth/types'
import { firebaseAuth } from '@/integrations/firebase'

setPersistence(firebaseAuth, browserLocalPersistence)

export const firebaseSignIn = async ({ email, password }: LoginFormValues) => {
  const result = await signInWithEmailAndPassword(firebaseAuth, email, password)
  return result
}

export const firebaseSignUp = async ({ email, password }: UserFormValues) => {
  const result = await createUserWithEmailAndPassword(
    firebaseAuth,
    email,
    password,
  )
  return result
}

export const firebaseSignOut = async () => {
  await signOut(firebaseAuth)
}
