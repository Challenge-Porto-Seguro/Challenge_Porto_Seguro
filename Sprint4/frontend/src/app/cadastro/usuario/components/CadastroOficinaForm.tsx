export default function CadastroOficinaForm() {
    return(
      <form className="w-full flex flex-col items-center gap-14">
          <div className="w-2/3">
              <label className="mb-5">Nome <span className="text-red-600">*</span></label>
              <input type="text" name="" placeholder="Tecno Soluções" className="formulario_cadastro"/>
          </div>
          <div className="w-2/3">
              <label className="mb-5">CNPJ <span className="text-red-600">*</span></label>
              <input type="text" placeholder="XX.XXX.XXX/0001-XX" className="formulario_cadastro"/>
          </div>

          <div className="w-2/3">
              <label className="mb-5">INSCRIÇÃO ESTADUAL <span className="text-red-600">*</span></label>
              <input type="text" placeholder="388.108.598.269" className="formulario_cadastro"/>
          </div>

          <div className="w-2/3">
              <label className="mb-5">TELEFONE <span className="text-red-600">*</span></label>
              <input type="text" placeholder="(11) 91234-1234" className="formulario_cadastro"/>
          </div>
  
          <div className="w-2/3">
            <label className="mb-5">E-MAIL <span className="text-red-600">*</span></label>
            <input type="email" placeholder="joao@gmail.com" className="formulario_cadastro"/>
        </div>

        <div className="w-2/3">
            <label className="mb-5">SENHA <span className="text-red-600">*</span></label>
            <input type="password" placeholder="Minimo 6 caracteres" className="formulario_cadastro"/>
        </div>
  
          <div className="w-2/3 flex justify-between">
            <div>
                <button type="submit" className="bg-blue-600 p-3 pl-16 pr-16">Cadastrar</button>
            </div>
            <p className="w-1/3">Ao preencher o formulario acima você concorda com os nossos termos de uso e nossa politica de privacidade</p>
          </div>
      </form>
    )
  }