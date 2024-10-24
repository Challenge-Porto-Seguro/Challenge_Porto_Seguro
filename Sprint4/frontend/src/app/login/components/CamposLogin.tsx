"use client"
import { UserLogin } from "@/type"
import { useRouter } from "next/navigation"
import { useState } from "react"

export default function CamposLogin() {

    const [user, setUser] = useState<UserLogin>(
        {email: "", senha: ""}
    )
    const [errors, setErrors] = useState<UserLogin>({
        email: "", senha: ""
    })

    const [erroLogar, setErrorLogar] = useState(false)

    const validaFormulario = () => {
        let isValid = true

        const newError = {email: "", senha: ""}

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

    const loginChange = (e:React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target
        setUser({...user, [name]:value})
        setErrorLogar(false)
    }

    const loginSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        
        try {
            if(validaFormulario()){
            
                const cabecalho = {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        "Origin": "http://localhost:3000" // Adiciona cabeçalho Origin
                    },
                    body: JSON.stringify(user)
                };
                
                const response = await fetch(`http://localhost:5000/cliente/login`, cabecalho);
        
                if (response.ok) {
                    const data = await response.json()
                    localStorage.setItem("id", data[0])
                    navigate.push("/")
                    setErrorLogar(false)
                } else {
                    setErrorLogar(true)
                }
            } 
        } catch (error) {
            console.error(error);
        }
            
    };

    return (
        <form onSubmit={loginSubmit} className="w-full flex flex-col justify-center items-center">
            <div className="w-2/3 m-5">
                <label className="m-2 text-xl">Digite seu e-mail:</label>
                <input className="w-full p-3 bg-blue-200 rounded-3xl placeholder:text-black" type="email" placeholder="E-mail" name="email" onChange={loginChange} />
                {errors.email && <p className="text-red-700 m-2">{errors.email}</p>}
            </div>
            
            <div className="w-2/3 m-5">
                <label className="m-2 text-xl">Digite sua senha:</label>
                <input className="w-full p-3 bg-blue-200 rounded-3xl placeholder:text-black" type="password" placeholder="Senha" name="senha" onChange={loginChange}/>
                {errors.senha && <p className="text-red-700 m-2">{errors.senha}</p>}
                {erroLogar && <p className="text-red-700 m-2">Email ou senha incorretos</p>}
                <p className="text-indigo-500 m-2">Esqueceu sua senha?</p>
            </div>
            <button type="submit" className="bg-blue-400 p-3 pl-28 pr-28 rounded-3xl text-white text-xl">Entrar</button>
        </form>
                
    )
}