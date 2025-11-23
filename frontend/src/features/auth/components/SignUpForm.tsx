import { useNavigate } from '@tanstack/react-router'
import { useState } from 'react'
import { Label } from '@radix-ui/react-label'
import { AlertCircle } from 'lucide-react'
import type { FormEvent } from 'react'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import useAuth from '@/features/auth/hooks/useAuth'
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from '@/components/ui/card'
import { Alert, AlertDescription, AlertTitle } from '@/components/ui/alert'

export default function SignUpForm() {
  const navigate = useNavigate()
  const { signUp, loading } = useAuth()
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [displayName, setDisplayName] = useState('')
  const [error, setError] = useState<string | null>(null)

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault()
    setError(null)

    try {
      await signUp({ displayName, email, password })
      navigate({ to: '/' })
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to sign in')
    }
  }

  return (
    <Card>
      <CardHeader>
        <CardTitle>Sign up</CardTitle>
        <CardDescription>Welcome! Sign up for an account here.</CardDescription>
      </CardHeader>
      <CardContent className="grid gap-6">
        {error && (
          <Alert variant="destructive">
            <AlertCircle className="h-4 w-4" />
            <AlertTitle>Error</AlertTitle>
            <AlertDescription>{error}</AlertDescription>
          </Alert>
        )}
        <div className="grid gap-3">
          <Label htmlFor="tabs-demo-name">Display Name</Label>
          <Input
            id="tabs-demo-name"
            value={displayName}
            onChange={(e) => setDisplayName(e.target.value)}
            placeholder="Display name"
            disabled={loading}
            required
          />
        </div>
        <div className="grid gap-3">
          <Label htmlFor="tabs-demo-name">Email</Label>
          <Input
            id="tabs-demo-name"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Email"
            disabled={loading}
            required
          />
        </div>
        <div className="grid gap-3">
          <Label htmlFor="tabs-demo-username">Password</Label>
          <Input
            id="tabs-demo-username"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Password"
            type="password"
            disabled={loading}
            required
          />
        </div>
      </CardContent>
      <CardFooter>
        <Button onClick={handleSubmit} disabled={loading}>
          Sign Up
        </Button>
      </CardFooter>
    </Card>
  )
}
