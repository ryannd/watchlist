import { createFileRoute } from '@tanstack/react-router'
import { Button } from '@/components/ui/button'
import useAuth from '@/features/auth/hooks/useAuth'

export const Route = createFileRoute('/')({
  component: Index,
})

function Index() {
  const { signOut } = useAuth()

  return (
    <div className="p-2">
      <h3>Welcome Home!</h3>
      <Button onClick={signOut}>Sign Out</Button>
    </div>
  )
}
