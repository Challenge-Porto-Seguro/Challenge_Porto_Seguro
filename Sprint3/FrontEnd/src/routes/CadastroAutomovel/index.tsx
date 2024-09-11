import Formulario from "../../components/Formulario"
import { CadastroPageStyle, FormularioCadastroVeiculo } from "../../styledConfig"
import { CriacaoFormulario } from "../../types"

export default function CadastroAutomovel() {

    const formularioCadastroVeiculo: CriacaoFormulario[] = [
        {id: "marca", nomeCampo: "Marca", tipo: "text", placeHolder: "Digite a marca de seu veiculo"},
        {id: "modelo", nomeCampo: "Modelo", tipo: "text", placeHolder: "Digite o modelo do seu veiculo"},
        {id: "ano", nomeCampo: "Ano", tipo: "date", placeHolder: "Digite o ano de seu veiculo"},
        {id: "placa", nomeCampo: "Placa", tipo: "text", placeHolder: "Digite a placa do seu veiculo"}
    ]
     document.title ="Cadastro de Automovel"
    return (
       
        <CadastroPageStyle>
            <h1>Cadastro de veiculo</h1>
            <FormularioCadastroVeiculo>
                {
                    formularioCadastroVeiculo.map((campo, index) => (
                        <Formulario key={index} id={campo.id} nomeCampo={campo.nomeCampo} tipo={campo.tipo} placeHolder={campo.placeHolder} />
                    ))
                }
                <button className="botao_cadastro">Cadastrar</button>
            </FormularioCadastroVeiculo>
        </CadastroPageStyle>
    )
}