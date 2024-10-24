import { FeadbackProps } from "@/type";

export default function Feadback({nome, descricao, cidade, estado}: FeadbackProps) {
  return(
    <div>
        <h1>{nome}</h1>
        <h3>{descricao}</h3>
        <h3>{cidade}</h3>
        <h3>{estado}</h3>
    </div>
  )
}