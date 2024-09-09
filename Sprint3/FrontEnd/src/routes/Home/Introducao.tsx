import logoPorto from "../../assets/logo_porto.webp"
import carro from "../../assets/carro_tela_inicial.jpeg"

export default function Introducao() {

    return (
        <section className="home__introducao">
                <article>
                    <img className="principal__logo" src={logoPorto} alt="Logo da porto" />
                    <h1 className="principal__titulo">Seu carro com o diagnostico e orçamento mais rapido e sem sair de casa</h1>
                </article>
                <img className="principal__imagem" src={carro} alt="imagem de carro" />
            </section>

    )
}