import { CardProps } from "@/type";
import Image from "next/image";

export default function Card({img, titulo, descricao}: CardProps) {
  return(
    <div className="flex flex-col items-center justify-center w-1/4 p-10">
        <Image className="rounded-3xl" src={img} alt={`imagem do ${titulo}`} />
        <h2 className="font-bold text-xl">{titulo}</h2>
        <p>{descricao}</p>
    </div>
  )
}