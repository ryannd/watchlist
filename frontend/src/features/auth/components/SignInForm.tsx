import { useNavigate } from '@tanstack/react-router'
import { useEffect, useState } from 'react'
import type { FormEvent } from 'react'
import { Button } from '@/components/ui/button'
import {
  Field,
  FieldDescription,
  FieldGroup,
  FieldLabel,
  FieldLegend,
  FieldSet,
} from '@/components/ui/field'
import { Input } from '@/components/ui/input'
import useAuth from '@/features/auth/hooks/useAuth'

export default function SignInForm() {
  const navigate = useNavigate()
  const { signIn, loading, user } = useAuth()
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    if (user) {
      navigate({ to: '/' })
    }
  }, [])

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault()
    setError(null)

    try {
      await signIn({ email, password })
      navigate({ to: '/' })
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to sign in')
    }
  }

  return (
    <div className="w-full md:w-[600px] p-8">
      <form onSubmit={handleSubmit}>
        <FieldGroup>
          <FieldSet>
            <FieldLegend>Sign In</FieldLegend>
            <FieldDescription>Login with email and password</FieldDescription>
            {error && <div className="text-red-500 text-sm mb-4">{error}</div>}
            <FieldGroup>
              <Field>
                <FieldLabel htmlFor="email-field">Email</FieldLabel>
                <Input
                  id="email-field"
                  required
                  placeholder="Email"
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  disabled={loading}
                />
              </Field>
              <Field>
                <FieldLabel htmlFor="password-field">Password</FieldLabel>
                <Input
                  id="password-field"
                  required
                  placeholder="Password"
                  type="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  disabled={loading}
                />
              </Field>
              <Field orientation="horizontal">
                <Button type="submit" disabled={loading}>
                  {loading ? 'Signing in...' : 'Submit'}
                </Button>
              </Field>
            </FieldGroup>
          </FieldSet>
        </FieldGroup>
      </form>
    </div>
  )
}
