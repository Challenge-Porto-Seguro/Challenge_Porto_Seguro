import SetaVoltar from "../../components/SetaVoltar"
import { LoginPageStyle } from "../../styledConfig"

export default function Login() {
    document.title = "Login"
    return (
        <LoginPageStyle>
            <SetaVoltar />
            <section className="container">
                <article className="container__titulos">
                    <h1 className="container__titulos--titulo">Acesse</h1>
                    <h3 className="container__titulos--subtitulo">com e-mail e senha</h3>
                </article>
                <article>
                    <h2 className="nome__input">Digite seu e-mail:</h2>
                    <input className="input" type="email" placeholder="E-mail" />

                    <h2 className="nome__input">Digite sua senha:</h2>
                    <input className="input" type="password" placeholder="Senha" />
                    <p className="esqueceu__senha">Esqueceu sua senha?</p>
                    <div className="div__botao">
                        <button className="btn">Entrar</button>
                    </div>
                    
                </article>
            </section>
        </LoginPageStyle>
    )
}