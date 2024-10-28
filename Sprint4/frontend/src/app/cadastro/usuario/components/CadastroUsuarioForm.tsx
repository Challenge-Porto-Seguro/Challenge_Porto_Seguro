import { UserCadastro } from "@/type"
import { useRouter } from "next/navigation"
import { useState } from "react"
import Modal from "../../../../components/Modal"
import { MdSaveAs } from "react-icons/md"

export default function CadastroUsuarioForm() {

    const [user, setUser] = useState<UserCadastro>(
        {nome: "", cpf: "", email: "", senha: ""}
    )
    const [errors, setErrors] = useState<UserCadastro>({
        nome: "", cpf: "", email: "", senha: ""
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

        if(!user.email){
            newError.email = "Email é obrigatorio"
            isValid = false
        } else if (!/\S+@\S+\.\S+/.test(user.email)) {
            newError.email = 'Insira um e-mail válido.';
            isValid = false;
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

    const navigate = useRouter()

    const cadastroChange = (e:React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target
        setUser({...user, [name]:value})
        setErrors({...errors, [name]:""})
    }

    const cadastroSubmit = async () => {
        
        try {
            if(validaFormulario()){
            
                const cabecalho = {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        "Origin": "http://localhost:3000"
                    },
                    body: JSON.stringify(user)
                };
                
                const response = await fetch(`http://localhost:8080/Java_war/api/user`, cabecalho);
        
                if (response.ok) {
                    const data = await response.json()
                    sessionStorage.setItem("id", data.id)
                    navigate.push("/cliente")
                } else{
                    const data = await response.json()
                    const mensagem = data.message.toLowerCase()
                    const newError = { nome: "", cpf: "", email: "", senha: "" };
                    if(mensagem.includes("senha")){
                        newError.senha = "senha invalida"
                    } else if(mensagem.includes("email")){
                        newError.email = "email invalido"
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

  return(
    <div>
        <form onSubmit={modal} className="w-full flex flex-col items-center gap-14">
            <div className="w-2/3">
                <label className="mb-5">Nome <span className="text-red-600">*</span></label>
                <input type="text" name="nome" placeholder="Carlos Eduardo Alvez da Silva" className="formulario_cadastro" onChange={cadastroChange}/>
                {errors.nome && <p className="text-red-700 m-2">{errors.nome}</p>}
            </div>
            <div className="w-2/3">
                <label className="mb-5">CPF <span className="text-red-600">*</span></label>
                <input type="text" name="cpf" placeholder="123.456.789-12" className="formulario_cadastro" onChange={cadastroChange}/>
                {errors.cpf && <p className="text-red-700 m-2">{errors.cpf}</p>}
            </div>
            <div className="w-2/3">
                <label className="mb-5">E-MAIL <span className="text-red-600">*</span></label>
                <input type="email" name="email" placeholder="joao@gmail.com" className="formulario_cadastro" onChange={cadastroChange}/>
                {errors.email && <p className="text-red-700 m-2">{errors.email}</p>}
            </div>

            <div className="w-2/3">
                <label className="mb-5">SENHA <span className="text-red-600">*</span></label>
                <input type="password" name="senha" placeholder="Minimo 6 caracteres" className="formulario_cadastro" onChange={cadastroChange}/>
                {errors.senha && <p className="text-red-700 m-2">{errors.senha}</p>}
            </div>

            <div className="w-2/3 flex justify-between">
                <div>
                    <button type="submit" className="bg-blue-600 p-3 pl-16 pr-16">Cadastrar</button>
                </div>
                <p className="w-1/3">Ao preencher o formulario acima você concorda com os nossos termos de uso e nossa politica de privacidade</p>
            </div>
        </form>
        <Modal open={open} onClose={() => setOpen(false)}>
        <div className="text-center w-56">
            <MdSaveAs size={56} className="mx-auto text-red-500" />
            <h3 className="text-lg font-black text-gray-800">Cadastrar Usuario?</h3>
            <p className="text-gray-500 text-sm">Voce tem certeza que deseja cadastrar o usuario {user.nome}</p>
        </div>

        <div className="flex gap-16">
            <button className="btn .btn-danger w-full" onClick={cadastroSubmit}>Sim</button>
            <button className="btn btn-light w-full" onClick={() => setOpen(false)} >Não</button>
        </div>
    </Modal> 
    </div>
    
  )
}