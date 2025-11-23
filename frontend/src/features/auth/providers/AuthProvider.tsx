import { onAuthStateChanged } from 'firebase/auth'
import React, { useEffect, useState } from 'react'
import type { User } from 'firebase/auth'
import type {
  IAuth,
  LoginFormValues,
  UserFormValues,
} from '@/features/auth/types'
import { AuthContext } from '@/features/auth/store/AuthContext'
import { firebaseAuth } from '@/integrations/firebase'
import {
  firebaseSignIn,
  firebaseSignOut,
  firebaseSignUp,
} from '@/integrations/firebase/auth'

const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [currentUser, setCurrentUser] = useState<User | null>(null)
  const [isLoading, setIsLoading] = useState<boolean>(false)
  const [isAuthLoading, setIsAuthLoading] = useState<boolean>(true)

  const signUp = async (creds: UserFormValues) => {
    setIsLoading(true)
    try {
      await firebaseSignUp({
        email: creds.email,
        password: creds.password,
        displayName: creds.displayName,
      })
    } finally {
      setIsLoading(false)
    }
  }

  const signIn = async (creds: LoginFormValues) => {
    setIsLoading(true)
    try {
      await firebaseSignIn({ email: creds.email, password: creds.password })
    } finally {
      setIsLoading(false)
    }
  }

  const signOut = async () => {
    setIsLoading(true)
    try {
      await firebaseSignOut()
      setCurrentUser(null)
    } finally {
      setIsLoading(false)
    }
  }

  const authValues: IAuth = {
    user: currentUser,
    loading: isLoading,
    signIn,
    signUp,
    signOut,
  }

  useEffect(() => {
    const unsubscribe = onAuthStateChanged(firebaseAuth, (user) => {
      if (user) {
        user.getIdToken(true).then((token) => {
          localStorage.setItem('auth-token', token)
        })
      } else {
        localStorage.removeItem('auth-token')
      }
      setCurrentUser(user)
      setIsAuthLoading(false)
    })
    return unsubscribe
  }, [])

  if (isAuthLoading) return <></>

  return (
    <AuthContext.Provider value={authValues}>{children}</AuthContext.Provider>
  )
}

export default AuthProvider
