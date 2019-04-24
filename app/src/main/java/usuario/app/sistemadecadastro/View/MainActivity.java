package usuario.app.sistemadecadastro.View;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;
import usuario.app.sistemadecadastro.Model.DatabaseHelper;
import usuario.app.sistemadecadastro.Model.Registro;

public class MainActivity extends Activity {
    private ArrayList<Registro> aRegistro = new ArrayList<>();
    TelaPrincipal tela_principal;
    TelaCadastroUsuario tela_cadastro;
    TelaListagemUsuarios tela_listagem;
    TelaAlterarCadastro tela_alteracao;
    TelaExcluirCadastro tela_exclusao;
    private DatabaseHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tela_principal = new TelaPrincipal(this);
        tela_cadastro = new TelaCadastroUsuario(this, tela_principal);
        tela_listagem = new TelaListagemUsuarios(this, tela_principal);
        tela_alteracao = new TelaAlterarCadastro(this,tela_principal);
        tela_exclusao = new TelaExcluirCadastro(this, tela_principal);
        tela_principal.setTelaCadastro(tela_cadastro);
        tela_principal.setTelaListagem(tela_listagem);
        tela_principal.setTelaAlteracao(tela_alteracao);
        tela_principal.setTelaExclusao(tela_exclusao);
        tela_principal.CarregarTela();
        dh = new DatabaseHelper(this);
        //Inserindo os valores da tabela na ArrayList
        aRegistro = dh.recuperaTodosRegistros();
    }

    @Override
    protected void onStop(){
        super.onStop();
        System.out.println("Parando - OnStop");
        dh.limpaTabela();
        dh.insereRegistro(aRegistro);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Toast.makeText(getApplicationContext(), "Bem-Vindo de Volta", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
            for (int i = 0; i < aRegistro.size(); i++){
                //Limpando a tabela de cadastro antes de inserir os dados da ArrayList novamente
                dh.limpaTabela();
                dh.insereRegistro(aRegistro);
        }
    }

    public ArrayList<Registro> getaRegistros(){
        return aRegistro;
    }

    public void ExibirMensagem(String msg){
        AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this);
        d.setTitle("Aviso");
        d.setMessage(msg);
        d.setNeutralButton("OK",null);
        d.show();
    }
}