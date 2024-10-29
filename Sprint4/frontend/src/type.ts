import { StaticImageData } from "next/image";

export type UserLogin = {
    email: string;
    senha: string;
}

export type User = {
    nome: string
    cpf: string
    email: string
    senha: string
}

export type CardProps = {
    img: StaticImageData
    titulo: string
    descricao: string
}


export type UserCadastro = {
    nome: string
    cpf: string
    email: string
    senha: string

}

export type alterar = {
    nome: string
    cpf: string
    senha: string
}

export type CardCarrosProps = {
    placa:string 
    marca:string 
    modelo:string 
    data:string
}

export type Carro = {
    id:number
    placa:string 
    marca:string 
    modelo:string 
    data:string
}

export type CarroResponse = {
    cd_pessoa:number
    placa:string 
    marca:string 
    modelo:string 
    dt_veiculo:string
}
