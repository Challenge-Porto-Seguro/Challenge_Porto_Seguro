export default function CamposLogin() {
    return (
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
    )
}