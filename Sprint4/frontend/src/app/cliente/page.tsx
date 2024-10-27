"use client"
import { User } from "@/type"
import { useRouter } from "next/navigation"
import { useEffect, useState } from "react"

export default function Cliente() {
    const [usuario, setUsuario] = useState<User>({nome: ""})
    const navigate = useRouter()

    useEffect(() => {
        const verificaLogado = async () =>{
            const usuarioLogado = sessionStorage.getItem("id")
            
            if(usuarioLogado){
            try{
                const response = await fetch(`http://localhost:8080/Java_war/api/user/${usuarioLogado}`)
                const data = await response.json()
                const nome = data["nome"]
                setUsuario({nome})
            } catch(error){
                console.error(error)
            }
            } else {
            navigate.push("/login")
            }
        }

        verificaLogado()
    }, [])


  return(
    <main>
        <h1>Bem vindo {usuario.nome}</h1>
    </main>
  )
}