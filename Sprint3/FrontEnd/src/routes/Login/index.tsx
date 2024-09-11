import { Link } from "react-router-dom"
import SetaVoltar from "../../components/SetaVoltar"
import { LoginPageStyle } from "../../styledConfig"
import CamposLogin from "./CamposLogin"

export default function Login() {
    document.title = "Login"
    return (
        <LoginPageStyle>
            <SetaVoltar />
            <section className="container">
                <article className="container__titulos">
                    <h1 className="container__titulos--titulo">Acesse</h1>
                    <h3 className="container__titulos--subtitulo">com e-mail e senha</h3>
                    <Link to="/cadastro/usuario">Não tem conta clique aqui para cadastrar</Link>
                </article>
                <CamposLogin />
            </section>
        </LoginPageStyle>
    )
}