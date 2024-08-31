import logoPorto from "../../assets/logo_porto.webp"
import carro from "../../assets/carro_tela_inicial.jpeg"

export default function Home() {
    return (
        <main>
            <section>
                <article>
                    <img src={logoPorto} alt="Logo da porto" />
                    <h1>Seu carro com o diagnostico e orçamento mais rapido e sem sair de casa</h1>
                </article>
                <img src={carro} alt="imagem de carro" />
            </section>
        </main>
    )
}