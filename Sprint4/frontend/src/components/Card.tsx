import { CardProps } from "@/type";
import Image from "next/image";

export default function Card({img, titulo, descricao}: CardProps) {
  return(
    <div className="flex flex-col w-1/4 p-10 gap-5">
        <Image className="rounded-3xl border-2" src={img} alt={`imagem do ${titulo}`} />
        <h2 className="font-bold text-xl">{titulo}</h2>
        <p>{descricao}</p>
    </div>
  )
}