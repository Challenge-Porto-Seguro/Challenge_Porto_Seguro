"use client"
import { useEffect, useState } from "react"
import { Carro } from "@/type"
import CardCarros from "./CardCarros"

export default function Carrosel({ carros }: { carros: Carro[] }) {
    const [atual, setAtual] = useState(0);
    const [carrosPorPagina, setCarrosPorPagina] = useState(1);

    // Verifica o tamanho da tela e ajusta `carrosPorPagina` quando necessário
    useEffect(() => {
        const handleResize = () => {
            if (window.innerWidth >= 768) {
                setCarrosPorPagina(3);
            } else {
                setCarrosPorPagina(1);
            }
        };

        // Define o valor inicial e adiciona o event listener para redimensionamento
        handleResize();
        window.addEventListener('resize', handleResize);

        // Remove o event listener quando o componente é desmontado
        return () => window.removeEventListener('resize', handleResize);
    }, []);

    // Calcula o número total de páginas
    const totalPaginas = Math.ceil(carros.length / carrosPorPagina);

    // Funções para navegar entre os itens
    const proximo = () => {
        setAtual((prev) => (prev + 1) % totalPaginas);
    };

    const anterior = () => {
        setAtual((prev) => (prev - 1 + totalPaginas) % totalPaginas);
    };

    return (
        <div className="w-full">
            <div className="overflow-hidden relative">
                <div
                    className="flex transition-transform ease-out duration-500"
                    style={{ transform: `translateX(-${atual * (100 / carrosPorPagina)}%)` }}
                >
                    {carros.map((c) => (
                        <div key={c.id} className="flex-none w-full md:w-1/3 px-2">
                            <CardCarros id={c.id} placa={c.placa} marca={c.marca} modelo={c.modelo} data={c.data} />
                        </div>
                    ))}
                </div>
            </div>

            {/* Controles para navegar */}
            <div className="flex justify-between">
                <button className="text-5xl" onClick={anterior}>{" < "}</button>
                <button className="text-5xl" onClick={proximo}>{" > "}</button>
            </div>
            
        </div>
    );
}