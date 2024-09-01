import setaEnviar from "../../assets/iconeEnviar.png"
import { ChatBotPageStyle } from "../../styledConfig"

export default function ChatBot(){
    document.title = "ChatBot"
    return (
        <ChatBotPageStyle>
            <h1 className="titulo">AUTO - BOT</h1>
            <div className="container">
                <input className="container__input" type="text" placeholder="Fale sobre o problema" />
                <button className="container__btn"><img className="container__imagem" src={setaEnviar} alt="seta de enviar" /></button>
            </div>
            
        </ChatBotPageStyle>
    )
}