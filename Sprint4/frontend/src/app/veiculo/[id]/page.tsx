"use client"
import { Carro } from "@/type"
import { useEffect, useState } from "react"
import InformacoesVeiculo from "./components/InformacoesVeiculo"

export default function Veiculo({params} : {params:{id:number}}) {

  const [carro, setCarro] = useState<Carro>({
    id: 0, data: "", marca: "", modelo: "", placa: ""
  })

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
  
  return(
    <main>
      
      <InformacoesVeiculo id={carro.id} data={carro.data} marca={carro.marca} modelo={carro.modelo} placa={carro.placa} reload={() => reload}/>

    </main>
  )
}