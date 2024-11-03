"use client"
import { Carro, Diagnostico } from "@/type"
import { useEffect, useState } from "react"
import InformacoesVeiculo from "./components/InformacoesVeiculo"
import Link from "next/link"
import Carrosel from "./components/Carrosel"

export default function Veiculo({params} : {params:{id:number}}) {

  const [carro, setCarro] = useState<Carro>({
    id: 0, data: "", marca: "", modelo: "", placa: ""
  })

  const [diagnosticos, setDiagnosticos] = useState<Diagnostico[]>([])

  const id = params.id
  useEffect(
    () => {
        const chamadaApi = async () => {
            const response = await fetch(`http://localhost:8080/Java_war/api/automovel/${id}`)
            const data = await response.json()
            const car = {
              id: data.id,
              data: data.dt_veiculo,
              marca: data.marca,
              modelo: data.modelo,
              placa: data.placa
            }   
            setCarro(car)
        }
        chamadaApi()
    }, [id])

    const reload = ()=> {
      window.location.reload()
  }

  useEffect(() => {
    const puxarDiagnostico = async () => {
        try{
            const response = await fetch(`http://localhost:8080/Java_war/api/diagnostico/buscar_todos/${sessionStorage.getItem("id")}`)
            if (response.ok) {
                const data = await response.json();
                
                const diagnosticoList = []
                for (let i = 0; i < data.length; i++) {
                    const diagnostico:Diagnostico = {
                        id: data[i].id, cd_automovel: data[i].cd_automovel, marca: data[i].marca, cd_oficina: data[i].cd_oficina, descricao: data[i].descricao
                        ,dt_fim: data[i].dt_fim, dt_inicio: data[i].dt_inicio, nomeOficina: data[i].nomeOficina, status: data[i].status 
                    }
                    diagnosticoList.push(diagnostico)               
                }
                setDiagnosticos(diagnosticoList)
            } else {
                const errorMessage = await response.json();
                console.error("Erro na resposta:", response.status, errorMessage);
            }
           
        } catch(error){
            console.error(error)
        }
    }
    puxarDiagnostico()
  }, [])
  
  return(
    <main>
      <InformacoesVeiculo id={carro.id} data={carro.data} marca={carro.marca} modelo={carro.modelo} placa={carro.placa} reload={() => reload}/>
      <Link href={"/chatbot"}>Iniciar diagnostico</Link>
      <Carrosel diagnosticos={diagnosticos} />
    </main>
      
  )
}