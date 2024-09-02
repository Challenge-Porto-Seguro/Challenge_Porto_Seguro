import { Link } from "react-router-dom"
import setaVoltar from "../assets/icone_voltar.svg"
import { SetaVoltarStyle } from "../styledConfig"

export default function SetaVoltar(){
    return (
        <SetaVoltarStyle>
            <Link to="/" className="link"><img className="link__imagem" src={setaVoltar} alt="seta de voltar" /></Link>
        </SetaVoltarStyle>
    )
}
