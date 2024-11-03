import { CardDiagnostico } from "@/type";

export default function CardDiagnosticos({id, descricao, dt_inicio, oficina, status} : CardDiagnostico) {
  return(
    <div>
        <h1>{id}</h1>
        <h1>{descricao}</h1>
        <h1>{dt_inicio}</h1>
        <h1>{oficina}</h1>
        <h1>{status}</h1>
    </div>
  )
}