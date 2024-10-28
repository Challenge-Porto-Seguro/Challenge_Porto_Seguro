import Modal from "@/components/Modal";
import { alterar, User } from "@/type";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { GrUpdate } from "react-icons/gr";
import { MdBlock, MdSaveAs } from "react-icons/md";

export default function InformacaoUsuario({nome, cpf, email, senha, reload}: User & {reload: () => void}) {

    const[alteraCampo, setAlteraCampo] = useState(false)

    const [user, setUser] = useState<alterar>(
        {nome: "", cpf: "", senha: ""}
    )

    useEffect(() => {
        setUser({nome, cpf, senha})
    },[nome, cpf, senha])

    const [errors, setErrors] = useState<alterar>({
        nome: "", cpf: "", senha: ""
    })
    const [open, setOpen] = useState(false)

    const modal = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if(validaFormulario()){
            setOpen(true)
        }
    }

    const validaFormulario = () => {
        let isValid = true;
        const newError = { nome: "", cpf: "", email: "", senha: "" };

        if(!user.nome){
            newError.nome = "Nome é obrigatorio"
            isValid = false
        }

        if(!user.cpf){
            newError.cpf = "CPF é obrigatorio"
            isValid = false
        }
   
        if(!user.senha){
            newError.senha = "Senha é obrigatoria"
            isValid = false
        } else if (user.senha.length < 6){
            newError.senha = "Senha deve ser maior que 6 caracteres"
            isValid = false
        }

        setErrors(newError)
        return isValid
    }

    const updateChange = (e:React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target
        setUser({...user, [name]:value})
        setErrors({...errors, [name]:""})
    }

    const updateSubmit = async () => {
        try {
            if(validaFormulario()){
            
                const cabecalho = {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json",
                        "Origin": "http://localhost:3000"
                    },
                    body: JSON.stringify(user)
                };
                
                const response = await fetch(`http://localhost:8080/Java_war/api/user/${sessionStorage.getItem("id")}`, cabecalho);
        
                if (response.ok) {
                    reload()
                } else{
                    const data = await response.json()
                    const mensagem = data.message.toLowerCase()
                    const newError = { nome: "", cpf: "", senha: "" };
                    if(mensagem.includes("senha")){
                        newError.senha = "senha invalida"
                    } else if(mensagem.includes("cpf")){
                        newError.cpf = "cpf invalido"
                    }
                    setErrors(newError)
                }
                setOpen(false)
            } 
        } catch (error) {
            console.error(error);
        }
            
    };

    const alterarCampo = (e: React.MouseEvent<HTMLButtonElement>) => {
        e.preventDefault()
        setAlteraCampo(!alteraCampo)
    }

    const limparCampo = (e: React.MouseEvent<HTMLButtonElement>) => {
        e.preventDefault();
        setUser({ ...user, senha: "" });
    };

  return(
    <div className="grow flex flex-col justify-center items-center">
        <h1 className="font-bold text-4xl">Informações da conta</h1>
        <div className="flex gap-4 w-1/6 mt-5">
            <h2>Email:</h2>
            <div className="flex gap-4 items-center bg-gray-300 pl-3 pr-3 border-2 border-black cursor-not-allowed">
                <p>{email}</p>
                <MdBlock />
            </div>
        </div>    
        <form onSubmit={modal} className="flex flex-col items-center justify-center">
            <div className="flex gap-4 mt-5">
                <label>Nome:</label>
                <input type="text" value={user.nome} name="nome" disabled={!alteraCampo} onChange={updateChange} className={`border-2 border-black pl-3 pr-3 ${!alteraCampo && "cursor-not-allowed bg-gray-300"}`}/>
            </div>
            <div className="flex gap-4 mt-5">
                <label>Cpf:</label>
                <input type="text" name="cpf" value={user.cpf} disabled={!alteraCampo} onChange={updateChange} className={`border-2 border-black pl-3 pr-3 ml-5 ${!alteraCampo && "cursor-not-allowed bg-gray-300"}`}/>
            </div>
            <div className="flex gap-4 mt-5">
                <label>Senha:</label>
                <input type="password" value={user.senha} name="senha" disabled={!alteraCampo} onChange={updateChange} className={`border-2 border-black pl-3 pr-3 ${!alteraCampo && "cursor-not-allowed bg-gray-300"}`}/>
                {alteraCampo && <button onClick={limparCampo}>Limpar</button>}
                
            </div>
            <div className="mt-5">
                <button type="submit" className="border border-black p-3">Atualizar</button>
                <button onClick={alterarCampo} className="ml-3 border border-black p-3">Alterar</button>
            </div>
        </form> 

        <Modal open={open} onClose={() => setOpen(false)}>
            <div className="text-center w-56">
                <GrUpdate size={56} className="mx-auto text-red-500" />
                <h3 className="text-lg font-black text-gray-800">Atualizar Usuario?</h3>
                <p className="text-gray-500 text-sm">Voce tem certeza que deseja atualizar os campos</p>
            </div>

            <div className="flex gap-16">
                <button className="btn .btn-danger w-full" onClick={updateSubmit}>Sim</button>
                <button className="btn btn-light w-full" onClick={() => setOpen(false)} >Não</button>
            </div>
        </Modal> 
    </div>
    
  )
}