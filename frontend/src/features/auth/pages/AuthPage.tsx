import { TabsContent } from '@radix-ui/react-tabs'
import { useNavigate } from '@tanstack/react-router'
import { useEffect } from 'react'
import { Tabs, TabsList, TabsTrigger } from '@/components/ui/tabs'
import SignInForm from '@/features/auth/components/SignInForm'
import SignUpForm from '@/features/auth/components/SignUpForm'
import useAuth from '@/features/auth/hooks/useAuth'

export const AuthPage = () => {
  const { user } = useAuth()
  const navigate = useNavigate()

  useEffect(() => {
    if (user) {
      navigate({ to: '/' })
    }
  }, [])

  return (
    <div className="h-screen w-screen max-h-screen max-w-screen flex justify-center items-center">
      <div className="w-full md:w-[600px] p-8">
        <Tabs defaultValue="signIn">
          <TabsList>
            <TabsTrigger value="signIn">Sign In</TabsTrigger>
            <TabsTrigger value="signUp">Sign Up</TabsTrigger>
          </TabsList>
          <TabsContent value="signIn">
            <SignInForm />
          </TabsContent>
          <TabsContent value="signUp">
            <SignUpForm />
          </TabsContent>
        </Tabs>
      </div>
    </div>
  )
}
