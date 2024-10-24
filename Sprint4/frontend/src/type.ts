import { StaticImageData } from "next/image";

export type UserLogin = {
    email: string;
    senha: string;
}

export type User = {
    nome: string
}

export type CardProps = {
    img: StaticImageData
    titulo: string
    descricao: string
}

export type FeadbackProps = {
    nome: string
    descricao: string
    cidade: string
    estado: string
}