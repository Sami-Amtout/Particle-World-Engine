# Particle-World-Engine
Designed a lightweight physics engine (fall, flow, swap, dissolve) with precise grid-state updates.
Constructed an OO hierarchy (ParticleBase to Particle to Fluid/Sand/Water/Steel/Acid) making shared and specialized behaviors (falling, flowing, density-based swaps, and acid dissolution). 
Built small scenario-based generators (2x2 – 5x5) for systematic and comprehensive unit tests (constructor invariants, null/edge bounds, N+1 branch coverage for loop conditions) catching regressions in velocity resets of particles, particle swap logic, and simulator bounds. 
