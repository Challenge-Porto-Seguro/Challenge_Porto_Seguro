"use client"
import { User } from "@/type"
import { useRouter } from "next/navigation"
import { useEffect, useState } from "react"
import InformacaoUsuario from "./components/InformacaoUsuario"

export default function Cliente() {
    const [usuario, setUsuario] = useState<User>({
        nome: "", email: "", cpf: "", senha: ""
    })

    const reload = ()=> {
        window.location.reload()
    }
    const navigate = useRouter()

    useEffect(() => {
        const verificaLogado = async () =>{
            const usuarioLogado = sessionStorage.getItem("id")
            
            if(usuarioLogado){
            try{
                const response = await fetch(`http://localhost:8080/Java_war/api/user/${usuarioLogado}`)
                const data = await response.json()
                const user:User = {
                    nome: data["nome"],
                    email: data["email"],
                    cpf: data["cpf"],
                    senha: data["senha"]
                }
                console.log("oi" + new Date());
                setUsuario(user)
                console.log(user);
                
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
    <main className="p-5 grow   ">
        <h1>Bem vindo {usuario.nome}</h1>
        <section>
            <InformacaoUsuario nome={usuario.nome} email={usuario.email} cpf={usuario.cpf} senha={usuario.senha} reload ={() => reload}/>
        </section>
    </main>
  )
}