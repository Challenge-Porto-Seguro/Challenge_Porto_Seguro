"use client"
import { useState } from "react"
import { Carro } from "@/type"
import CardCarros from "./CardCarros"

export default function Carrosel({ carros }: { carros: Carro[] }) {
    const [atual, setAtual] = useState(0)
    const carrosPorPagina = 3; // Número de carros por vez
    const totalPaginas = Math.ceil(carros.length / carrosPorPagina) + 1; // Total de páginas de carros

    const prev = () => {
        setAtual(atual === 0 ? totalPaginas - 1 : atual - 1)
    }

    const next = () => {
        setAtual(atual === totalPaginas - 1 ? 0 : atual + 1)
    }

    return (
        <div className="w-full">
            <div className="overflow-hidden relative">
                <div className="flex transition-transform ease-out duration-500"
                    style={{ transform: `translateX(-${atual * (100 / carrosPorPagina)}%)` }}>
                    {carros.map((c) => (
                        <div key={c.id} className="flex-none w-1/3 px-2"> {/* Cada carro ocupa 1/3 da largura com espaçamento */}
                            <CardCarros placa={c.placa} marca={c.marca} modelo={c.modelo} data={c.data} />
                        </div>
                    ))}
                </div>
                <div className="absolute inset-0 flex items-center justify-between p-4">
                    <button className="text-3xl font-black pb-1 rounded-full shadow bg-white/80 text-gray-800 hover:bg-white" onClick={prev}>{" < "}</button>
                    <button className="text-3xl font-black pb-1 rounded-full shadow bg-white/80 text-gray-800 hover:bg-white" onClick={next}>{" > "}</button>
                </div>
                <div className="absolute bottom-4 right-0 left-0">
                    <div className="flex items-center justify-center gap-2">
                        {Array.from({ length: totalPaginas }).map((_, i) => (
                            <div key={i} onClick={() => setAtual(i)} className={`transition-all w-3 h-3 bg-indigo-700 rounded-full ${atual === i ? "p-2" : "bg-opacity-5"}`}>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    )
}