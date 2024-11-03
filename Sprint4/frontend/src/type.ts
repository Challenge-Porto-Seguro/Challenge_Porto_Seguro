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
    cep: string,
    numero: number
    rua: string
    cidade: string
    estado: string
    bairro: string
}

export type OficinaRequest = {
    nome: string
    cnpj: string
    inscricaoEstadual: string
    email: string
    senha: string
    cep: string,
    numero: number
    rua: string
    cidade: string
    estado: string
    bairro: string
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
    cep: string,
    numero: number
}

export type OficinaCadastro = {
    nome: string
    cnpj: string
    inscricaoEstadual: string
    email: string
    senha: string
    cep: string,
    numero: number
}

export type Alterar = {
    nome: string
    cpf: string
    senha: string
    cep: string
    numero: number
}

export type AlterarOficina = {
    nome: string
    cnpj: string
    inscricaoEstadual: string
    senha: string
    cep: string
    numero: number
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

export type Diagnostico = {
    id: number
	cd_automovel: number
    marca: string
	descricao: string
	dt_inicio: string
	cd_oficina: number
    nomeOficina: string
	dt_fim: string
	status: string
}
