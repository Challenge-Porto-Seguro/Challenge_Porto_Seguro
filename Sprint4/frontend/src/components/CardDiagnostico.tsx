import { CardDiagnostico } from "@/type";
import Link from "next/link";
import { FaPen } from "react-icons/fa";

export default function CardDiagnosticos({id, descricao, dt_inicio, oficina, status} : CardDiagnostico) {
  return(
    <div className="border shadow-xl rounded-xl px-7 py-7 hover:border-blue-600 cursor-pointer">
        <Link href={`/diagnostico/${id}`} className="flex justify-end"><FaPen className="text-yellow-400"  /></Link>
        <h1 className="text-3xl mb-3">Descrição: <span className="uppercase text-red-600">{descricao}</span></h1>
        <h2 className="text-xl uppercase mb-3">Data de inicio: {dt_inicio}</h2>
        <h2 className="text-xl mb-6">Oficina: {oficina}</h2>
        <p>Status: {status}</p>
    </div>
  )
}