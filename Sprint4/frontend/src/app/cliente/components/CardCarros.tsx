import { CardCarrosProps } from "@/type";

export default function CardCarros({placa, marca, modelo, data} : CardCarrosProps) {
  return(
    <div className="border shadow-xl rounded-xl pr-28 pl-5 pt-6 pb-16 hover:border-blue-600 cursor-pointer">
        <h1 className="text-3xl mb-3">Marca: <span className="uppercase text-red-600">{marca}</span></h1>
        <h2 className="text-xl uppercase mb-3">Modelo: {modelo}</h2>
        <h2 className="text-xl mb-6">Placa: {placa}</h2>
        <p>Data do veiculo: {data}</p>
    </div>
  )
}