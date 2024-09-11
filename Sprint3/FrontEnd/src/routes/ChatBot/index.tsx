import { Link } from "react-router-dom"
import setaEnviar from "../../assets/iconeEnviar.png"
import Automovel from "../../components/Automovel"
import { ChatBotPageStyle } from "../../styledConfig"
import { ListaAutomoveis } from "../../types"

export default function ChatBot(){
    document.title = "ChatBot"

    const automoveis: ListaAutomoveis[] = [
        {marca: "Tesla", placa: "123-AAA"},
        {marca: "Uno", placa: "122-ABC"}
    ]
    return (
        <ChatBotPageStyle>
            <section className="automoveis">
                <h1 className="titulo__automoveis">Automoveis Cadastrado</h1>
                {
                    automoveis.map((automovel, index) => (
                        <Automovel key={index} marca={automovel.marca} placa={automovel.placa} />
                    ))
                }
                <Link to="/cadastro/automovel">Cadastrar Automovel</Link>
            </section>
            <section className="chatbot">
                <h1 className="titulo">AUTO - BOT</h1>
                <div className="container">
                    <input className="container__input" type="text" placeholder="Fale sobre o problema" />
                    <button className="container__btn"><img className="container__imagem" src={setaEnviar} alt="seta de enviar" /></button>
                </div>
            </section>
            
            
        </ChatBotPageStyle>
    )
}