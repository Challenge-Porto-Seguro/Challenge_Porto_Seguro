"use client"
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { User } from "../type";
import Introducao from "@/components/Introducao";
import SobreNos from "@/components/SobreNos";
import Card from "@/components/Card";
import carro from "@/imagem/carro_tela_inicial.jpeg"

export default function Home() {

  // const [usuario, setUsuario] = useState<User>({nome: ""})
  // const navigate = useRouter()

  // useEffect(() => {
  //     const verificaLogado = async () =>{
  //       const usuarioLogado = localStorage.getItem("id")

  //       if(usuarioLogado){
  //         try{
  //           const response = await fetch(`http://localhost:5000/cliente/${usuarioLogado}`)
  //           const data = await response.json()
  //           const nome = data["nm_nome"]
  //           setUsuario({nome})
  //           localStorage.removeItem("id")
  //         } catch(error){
  //           console.error(error)
  //         }
  //       } else {
  //         navigate.push("/login")
  //       }
  //     }

  //     verificaLogado()
  // }, [])
    return (
        <main className="grow">
            <Introducao />
            <section className="flex justify-center  gap-2 p-8">
              <Card img={carro} titulo="OI" descricao="Legal" />
              <Card img={carro} titulo="OI" descricao="Legal" />
              <Card img={carro} titulo="OI" descricao="Legal" />
            </section>
            <SobreNos />
        </main>
    )
}
