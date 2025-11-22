import { useContext } from 'react'
import { AuthContext } from '@/features/auth/store/AuthContext'

export default function useAuth() {
  const context = useContext(AuthContext)
  return context
}
