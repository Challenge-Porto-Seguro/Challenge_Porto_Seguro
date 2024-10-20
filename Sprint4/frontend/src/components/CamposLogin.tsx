export default function CamposLogin() {
    return (
        <form className="w-full flex flex-col justify-center items-center">
            <div className="w-2/3 m-5">
                <h2 className="m-2 text-xl">Digite seu e-mail:</h2>
                <input className="w-full p-3 bg-blue-200 rounded-3xl placeholder:text-black" type="email" placeholder="E-mail" />
            </div>
            
            <div className="w-2/3 m-5">
                <h2 className="m-2 text-xl">Digite sua senha:</h2>
                <input className="w-full p-3 bg-blue-200 rounded-3xl placeholder:text-black" type="password" placeholder="Senha" />
                <p className="text-indigo-500 m-2">Esqueceu sua senha?</p>
            </div>
            <button className="bg-blue-400 p-3 pl-28 pr-28 rounded-3xl text-white text-xl">Entrar</button>
        </form>
                
    )
}