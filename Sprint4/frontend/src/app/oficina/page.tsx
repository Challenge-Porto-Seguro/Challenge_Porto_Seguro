"use client"
import { Diagnostico, OficinaRequest } from "@/type";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import InformacaoOficina from "./components/InformacaoOficina";

export default function Oficina() {

    const [oficina, setOficina] = useState<OficinaRequest>({
        nome: "", email: "", cnpj: "", inscricaoEstadual: "", senha: "", cep:"", numero: 0, 
        bairro: "", cidade: "", estado: "", rua: ""
    })

    const [diagnosticos, setDiagnosticos] = useState<Diagnostico[]>([])

    useEffect(() => {
        const id = sessionStorage.getItem("id");
        if (id) {
            setDiagnosticos((prevCarro) => ({
                ...prevCarro,
                cd_pessoa: Number(id),
            }));
        }
    }, [])

    const navigate = useRouter()

    useEffect(() => {
        const verificaLogado = async () =>{
            const usuarioLogado = sessionStorage.getItem("id")
            if(usuarioLogado){
                try{
                    const response = await fetch(`http://localhost:8080/Java_war/api/oficina/${usuarioLogado}`)
                    if(response.ok){
                        const data = await response.json()
                        const oficina:OficinaRequest = {
                            nome: data["nome"],
                            email: data["email"],
                            cnpj: data["cnpj"],
                            inscricaoEstadual: data["inscricaoEstadual"],
                            senha: data["senha"],
                            cep: data["cep"],
                            bairro: data["bairro"],
                            cidade: data["cidade"],
                            estado: data["estado"],
                            numero: data["numeroCasa"],
                            rua: data["rua"]
                        }
                        setOficina(oficina)     
                    } else {
                        const data = await response.json()
                        console.log(data);
                    }
                   
                } catch(error){
                    console.error(error)
                }
            } else {
                navigate.push("/login")
            }
        }
        
        verificaLogado()
    }, [])

    
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

        
        
        setTimeout(() => {
            puxarDiagnostico();
        }, 500);
       
    }, [])


  return(
    <main className="p-5 grow">
    <h1 className="text-2xl md:text-4xl font-bold">Bem vindo {oficina.nome}</h1>
    <section className="border rounded-3xl shadow-xl md:w-2/4 m-auto mt-5 p-10">
        <InformacaoOficina nome={oficina.nome} email={oficina.email} cnpj={oficina.cnpj} inscricaoEstadual={oficina.inscricaoEstadual} senha={oficina.senha} bairro={oficina.bairro} cep={oficina.cep}
        cidade={oficina.cidade} estado={oficina.estado} numero={oficina.numero} rua={oficina.rua} />
    </section>
</main>
  )
}