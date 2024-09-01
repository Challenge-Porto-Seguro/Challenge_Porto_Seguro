import logoPorto from "../../assets/logo_porto.webp"
import carro from "../../assets/carro_tela_inicial.jpeg"
import { HomeStyle } from "../../styledConfig"
import Footer from "../../components/Footer"

export default function Home() {
    document.title = "Home"
    return (
        <HomeStyle>
            <section className="home__introducao">
                <article>
                    <img className="principal__logo" src={logoPorto} alt="Logo da porto" />
                    <h1 className="principal__titulo">Seu carro com o diagnostico e orçamento mais rapido e sem sair de casa</h1>
                </article>
                <img className="principal__imagem" src={carro} alt="imagem de carro" />
            </section>
            <section className="home__sobre-nos">
                <div className="container">
                    <h2 className="home__sobre-nos--titulo">Sobre nós</h2>
                </div >
                <p className="home__sobre-nos--paragrafo">BEM-VINDO AO AUTODIAG, O SEU ASSISTENTE VIRTUAL DE DIAGNÓSTICOS AUTOMOTIVOS!</p>
                <p className="home__sobre-nos--paragrafo">NA AUTODIAG, REVOLUCIONAMOS A MANEIRA COMO VOCÊ CUIDA DO SEU VEÍCULO. NOSSO APLICATIVO INOVADOR UTILIZA A INTELIGÊNCIA ARTIFICIAL PARA FORNECER DIAGNÓSTICOS PRECISOS E RÁPIDOS ATRAVÉS DE CHATBOTS. COM APENAS ALGUNS CLIQUES, VOCÊ PODE DESCREVER OS PROBLEMAS QUE ESTÁ ENFRENTANDO COM SEU CARRO, E NOSSO CHATBOT INTELIGENTE ANALISARÁ OS SINTOMAS PARA OFERECER UM DIAGNÓSTICO CONFIÁVEL.</p>
                <p className="home__sobre-nos--paragrafo">MAS NÃO PARAMOS POR AÍ! ALÉM DE IDENTIFICAR O PROBLEMA, A AUTODIAG AJUDA VOCÊ A ENCONTRAR AS MELHORES OPÇÕES DE ORÇAMENTO PARA O REPARO DO SEU VEÍCULO. CONECTAMOS VOCÊ COM OFICINAS E MECÂNICOS DE CONFIANÇA, OFERECENDO COTAÇÕES TRANSPARENTES E COMPETITIVAS. NOSSO OBJETIVO É TORNAR O PROCESSO DE MANUTENÇÃO AUTOMOTIVA SIMPLES, CONVENIENTE E ECONÔMICO.</p>
                <p className="home__sobre-nos--paragrafo">NOSSA MISSÃO É PROPORCIONAR UMA EXPERIÊNCIA LIVRE DE ESTRESSE E CONFIÁVEL PARA OS PROPRIETÁRIOS DE VEÍCULOS, UTILIZANDO TECNOLOGIA DE PONTA PARA DIAGNÓSTICOS AUTOMOTIVOS E SERVIÇOS DE ORÇAMENTO. QUEREMOS GARANTIR QUE SEU CARRO ESTEJA SEMPRE EM ÓTIMO ESTADO, SEM COMPLICAÇÕES E COM TOTAL TRANSPARÊNCIA.</p>
            </section>
            <Footer />
        </HomeStyle>
    )
}