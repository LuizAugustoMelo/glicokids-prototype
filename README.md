# Projeto de Aplicativo Android: GlicoKids (MVP)

## 1. Descrição do Projeto
O **GlicoKids** é um aplicativo Android voltado para o manejo gamificado do Diabetes Tipo 1 em crianças. O projeto atua como um assistente inteligente que facilita a contagem de carboidratos diária por meio da estimativa visual de pratos de comida. Além do cálculo de dosagem de insulina, o aplicativo possui um ecossistema de gamificação desenhado para incentivar a prática de atividades físicas, promovendo a saúde integral da criança e reduzindo o tempo de sedentarismo em frente às telas.

## 2. Problema que o Aplicativo Pretende Resolver
O manejo do Diabetes Tipo 1 na infância exige cálculos matemáticos constantes de carboidratos e dosagens de insulina a cada refeição. Erros nesse processo podem levar a quadros graves, como a hiperglicemia ou a hipoglicemia severa (que apresenta risco de coma). Além disso, o sedentarismo agrava a condição. O GlicoKids resolve a exaustão mental dos pais e a falta de engajamento das crianças, oferecendo um cálculo seguro (programado para estimativas conservadoras de carboidratos, mitigando riscos de superdosagem) e transformando a rotina de cuidados e exercícios em um jogo recompensador.

## 3. Plataforma Escolhida
Este aplicativo será desenvolvido nativamente para o sistema operacional **Android**, utilizando a IDE Android Studio. A escolha por um desenvolvimento nativo garante melhor performance, fluidez nas animações de gamificação e acesso otimizado à câmera do dispositivo.

## 4. Interface do Usuário (UI) e Interface do Administrador (Pais)
* **Interface da Criança (UI Principal):** Será lúdica, colorida e focada em recompensas. Contará com um botão central para a "Missão da Refeição" (tirar a foto do prato), um painel de conquistas (insígnias e créditos ganhos por atividades físicas) e desafios diários (ex: "Corra por 15 minutos para desbloquear um novo avatar").
* **Interface dos Responsáveis (Painel Administrativo):** Uma área protegida por senha/PIN onde os pais configuram os parâmetros clínicos (Fator de Sensibilidade à Insulina, Relação Insulina/Carboidrato, Meta Glicêmica) e visualizam o histórico de alimentação e atividades da criança.

## 5. Principais Funcionalidades do Aplicativo
1. **Módulo de Estimativa de Carboidratos:** Interface para captura de foto do prato de comida para estimativa da carga de carboidratos (com trava de segurança algorítmica para evitar superestimação).
2. **Calculadora de Bolus de Insulina:** Motor de cálculo automático de UIs de insulina baseado nas regras e fatores clínicos predefinidos pelos responsáveis.
3. **Módulo de Gamificação (Anti-Sedentarismo):** Sistema de missões físicas onde a criança ganha moedas virtuais e insígnias ao registrar e cumprir metas de exercícios, incentivando a saída do sofá.
4. **Dashboard dos Pais:** Gerenciamento de perfis, configuração de variáveis clínicas e histórico de saúde.

## 6. Design (Esboços e Wireframes)
O design será construído seguindo as diretrizes do Material Design do Google, focado em acessibilidade cognitiva para crianças. Os wireframes iniciais contemplam: 
- Tela de Abertura (Splash Screen com mascote).
- Tela de Perfil/Dashboard da Criança.
- Câmera embutida com guias visuais para o prato.
- Tela de Configuração Clínica (exclusiva para pais).

## 7. Desenvolvimento Acadêmico - Módulo 2
### Requisitos Implementados:
- **Navegação entre Activities**: Fluxo entre `MainActivity` e `ParentSecurityActivity` via `Intents`.
- **Passagem de Dados**: Uso de `Extras` para passar o nome do usuário entre telas.
- **Interação de Interface**: `AlertDialog` de segurança na abertura do app.
- **Ciclo de Vida (Lifecycle)**:
    - `onCreate()`: Inicialização da UI e binding.
    - `onStart() / onResume()`: Logs de monitoramento de foco.
    - `onPause() / onStop()`: Pausa de processos de sensor (simulado).
    - `onDestroy()`: Limpeza de memória.

### Governança (Gitflow):
- Branch `main`: Produção.
- Branch `staging`: Homologação (BETA).
- Branch `develop`: Desenvolvimento ativo.
- CI/CD configurado via GitHub Actions para validação de testes.
